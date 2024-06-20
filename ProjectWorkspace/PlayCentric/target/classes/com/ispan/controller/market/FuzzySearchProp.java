package com.ispan.controller.market;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import com.ispan.bean.market.Prop;

@WebServlet("/FuzzySearchProp")
public class FuzzySearchProp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchPropName =request.getParameter("searchPropName");
		HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
		List<Prop> props = (List<Prop>) session.getAttribute("props");
        List<Prop> searchOKprops = props.stream().filter(prop -> prop.getPropName().contains(searchPropName)).collect(Collectors.toList());
        request.setAttribute("searchOKprops", searchOKprops);
		request.getRequestDispatcher("/dynamicView/market/propSheet.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
