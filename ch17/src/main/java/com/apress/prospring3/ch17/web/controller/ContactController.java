package com.apress.prospring3.ch17.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apress.prospring3.ch17.domain.Contact;
import com.apress.prospring3.ch17.service.ContactService;
import com.apress.prospring3.ch17.web.form.ContactGrid;
import com.apress.prospring3.ch17.web.form.Message;
import com.apress.prospring3.ch17.web.util.UrlUtil;
import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/contacts")
public class ContactController {

	final Logger logger = LoggerFactory.getLogger(ContactController.class);

	
	@Autowired
	private ContactService contactService;

	
	/**
	 * i18n 지원 기능을 사용해 메시지를 가져오도록 MessageSource 인터페이스를 컨트롤러로 자동 연결
	 */
	@Autowired
	MessageSource messageSource;

	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		logger.info("Listing contacts");

		List<Contact> contacts = contactService.findAll();
		model.addAttribute("contacts", contacts);

		logger.info("No. of contacts: " + contacts.size());

		return "contacts/list";
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		Contact contact = contactService.findById(id);
		model.addAttribute("contact", contact);
		return "contacts/show";
	}

	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(@Valid Contact contact, BindingResult bindingResult,
			Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes, Locale locale) {
		logger.info("Updating contact");

		if (bindingResult.hasErrors()) {
			model.addAttribute("message",
					new Message("error", messageSource.getMessage("contact_save_fail", new Object[] {}, locale)));
			model.addAttribute("contact", contact);
			return "contacts/update";
		}

		model.asMap().clear();

		redirectAttributes.addFlashAttribute("message",
				new Message("success", messageSource.getMessage("contact_save_success", new Object[] {}, locale)));
		contactService.save(contact);

		return "redirect:/contacts/" + UrlUtil.encodeUrlPathSegment(contact.getId().toString(), request);
	}
	
	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("contact", contactService.findById(id));
		
		return "contacts/update";
	}
	
	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String create(@Valid Contact contact, BindingResult bindingResult, Model model, 
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value = "file", required = false) Part file) {
		logger.info("Creating contact");
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("message", new Message("error",
					messageSource.getMessage("contact_save_fail", new Object[]{}, locale)));
			model.addAttribute("contact", contact);
			return "contacts/create";
		}
		
		model.asMap().clear();
		
		redirectAttributes.addFlashAttribute("message", new Message("success",
				messageSource.getMessage("contact_save_success", new Object[]{}, locale)));
		
		logger.info("Contact id: " + contact.getId());
		
		// 파일 업로드 처리
		if (file != null) {
			logger.info("File name: " + file.getName());
			logger.info("File size: " + file.getSize());
			logger.info("File content type: " + file.getContentType());
			
			byte[] fileContent = null;
			
			try {
				InputStream inputStream = file.getInputStream();
				
				if (inputStream == null) {
					logger.info("File inputstream is null");
				}
				
				fileContent = IOUtils.toByteArray(inputStream);
				contact.setPhoto(fileContent);
			} catch (IOException ex) {
				logger.error("Error saving uploaded file");
			}
		}
		
		contactService.save(contact);
		
		logger.info("created contact id: " + contact.getId());
		
		return "redirect:/contacts/" + UrlUtil.encodeUrlPathSegment(contact.getId().toString(), httpServletRequest);
	}
	
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model model) {
		Contact contact = new Contact();
		model.addAttribute("contact", contact);
		
		return "contacts/create";
	}
	
	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ContactGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sortBy,
			@RequestParam(value = "sord", required = false) String order) {
		logger.info("Listing contacts for grid with page: {}, rows : {}", page, rows);
		logger.info("Listing contacts for grid with sort: {}, order : {}", sortBy, order);
		
		// orderBy 처리
		Sort sort = null;
		String orderBy = sortBy;
		
		if (orderBy != null && orderBy.equals("birthDateString")) {
			orderBy = "birthDate";
		}
		
		if (orderBy != null && order != null) {
			if (order.equals("desc")) {
				sort = new Sort(Sort.Direction.DESC, orderBy);
			} else {
				sort = new Sort(Sort.Direction.ASC, orderBy);
			}
		}
		
		// 현재 페이지에 대한 페이지 요청을 생성
		// 주의 - 스프링 데이터 JPA의 페이지 번호는 0부터 시작하지만
		// JqGrid의 페이지 번호는 1부터 시작
		PageRequest pageRequest = null;
		
		if (sort != null) {
			pageRequest = new PageRequest(page - 1, rows, sort);
		} else {
			pageRequest = new PageRequest(page - 1, rows);
		}
		
		Page<Contact> contactPage = contactService.findAllByPage(pageRequest);
		
		// JSON 데이터로 반환할 그리드 데이터를 생성
		ContactGrid contactGrid = new ContactGrid();
		
		contactGrid.setCurrentPage(contactPage.getNumber() + 1);
		contactGrid.setTotalPages(contactPage.getTotalPages());
		contactGrid.setTotalRecords(contactPage.getTotalElements());
		contactGrid.setContactData(Lists.newArrayList(contactPage.iterator()));
		
		return contactGrid;
	}
	
	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] downloadPhoto(@PathVariable("id") Long id) {
		Contact contact = contactService.findById(id);
		
		if (contact.getPhoto() != null) {
			logger.info("Downloading photo for id: {} with size: {}", contact.getId(), contact.getPhoto().length);
		}
		
		return contact.getPhoto();
	}

}
