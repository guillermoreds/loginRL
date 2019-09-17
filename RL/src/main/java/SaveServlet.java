import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		if (session != null) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String country = request.getParameter("country");

		 
		Emp e = new Emp();
		e.setName(name);
		e.setPassword(password);
		e.setEmail(email);
		e.setCountry(country);

		int status = EmpDao.save(e);
			if (status > 0) {
				out.print("<p>Record saved successfully!</p>");
				request.getRequestDispatcher("registrar.html").include(request, response);
			} else {
			out.println("Sorry! unable to save record");
			}
		} else {
			out.print("Please login first");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		out.close();
		
	}

}