package com.texts.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.texts.bean.TextsBean;
import com.texts.dao.TextsDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetAllTexts")
public class GetAllTexts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TextsDAO textsDAO = new TextsDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<TextsBean> txts = textsDAO.getAll();
			request.setAttribute("txts", txts);
			request.getRequestDispatcher("/jsp/GetAllTexts.jsp").forward(request, response);
			Collections.sort(txts, new Comparator<TextsBean>() {
				@Override
				public int compare(TextsBean t1, TextsBean t2) {
					return t1.getTextsId().compareTo(t2.getTextsId());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
