package com.ispan.controller.announcement;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.bean.announcement.Announcement;
import com.ispan.bean.announcement.AnnouncementCategory;
import com.ispan.dao.announcement.AnnouncementCategoryDAO;
import com.ispan.dao.announcement.AnnouncementDAO;

@Controller
public class AnnouncementController {
	
	@Autowired
	private AnnouncementDAO dao;
	
	@Autowired
	private AnnouncementCategoryDAO cDao;
	
	@GetMapping("/homepage.con")
	public String homepage(Model m) {
		List<Announcement> all = dao.getAll();
		m.addAttribute("announcements",all);
		return "announcement/homepage";
	}
	
	@GetMapping("/showAnnouncement.con")
	public String showAnnouncement(Model m,@RequestParam("id") int id) {
		Announcement anno = dao.getOne(id);
		m.addAttribute("announcement",anno);
		return "announcement/show-announcement";
	}
	
	@GetMapping("/backAnnouncement.con")
	public String backAnnouncement(Model m) {
		List<Announcement> all = dao.getAll();
		m.addAttribute("announcements",all);
		return "announcement/back-announcement";
	}
	
	@GetMapping("/getUpdateAnnouncement.con")
	public String getUpdateAnnouncement(Model m,@RequestParam("id") int id) {
		Announcement anno = dao.getOne(id);
		m.addAttribute("announcement",anno);
		List<AnnouncementCategory> allcategory = cDao.getAll();
		m.addAttribute("categorys",allcategory);
		return "announcement/get-update-announcement";
	}
	
	@GetMapping("/getInsertAnnouncement.con")
	public String getInsertAnnouncement(Model m) {
		List<AnnouncementCategory> allcategory = cDao.getAll();
		m.addAttribute("categorys",allcategory);
		return "announcement/get-insert-announcement";
	}
	
	@PostMapping("/insertAnnouncement.con")
	public String insertAnnouncement(Model m,@RequestParam("title")String title,
			@RequestParam("content")String content,
			@RequestParam("categoryId")Integer categoryId) {
		Announcement anno = new Announcement();
		AnnouncementCategory category = cDao.getOne(categoryId);
		anno.setTitle(title);
		anno.setContent(content);
		anno.setCategoryId(categoryId);
		anno.setAnnouncementCategory(category);
		dao.insert(anno);
		List<Announcement> all = dao.getAll();
		m.addAttribute("announcements",all);
		return "announcement/back-announcement";
	}
	
	@PostMapping("/updateAnnouncement.con")
	public String updateAnnouncement(Model m,@RequestParam("id") Integer id,
			@RequestParam("title")String title,
			@RequestParam("createDate")Date createDate,
			@RequestParam("content")String content,
			@RequestParam("categoryId")Integer categoryId) {
		Announcement anno = dao.getOne(id);
		AnnouncementCategory category = cDao.getOne(categoryId);
		anno.setTitle(title);
		anno.setContent(content);
		anno.setCreateDate(createDate);
		anno.setCategoryId(categoryId);
		anno.setAnnouncementCategory(category);
		dao.update(anno);
		List<Announcement> all = dao.getAll();
		m.addAttribute("announcements",all);
		return "announcement/back-announcement";
	}
	
	@GetMapping("/deleteAnnouncement.con")
	public String deleteAnnouncement(Model m,@RequestParam("id") int id) {
		dao.delete(id);
		List<Announcement> all = dao.getAll();
		m.addAttribute("announcements",all);
		return "announcement/back-announcement";
	}
	
}
