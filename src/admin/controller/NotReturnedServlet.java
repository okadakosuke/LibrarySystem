package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.beans.NotReturned;
import admin.service.NotReturnedService;

@WebServlet(urlPatterns = {"/admin/notReturned"})
public class NotReturnedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<NotReturned> notReturnedlists = new NotReturnedService().select();

		request.setAttribute("notReturnedlists", notReturnedlists);


		request.getRequestDispatcher("/admin/notReturned.jsp").forward(request, response);
	}
}