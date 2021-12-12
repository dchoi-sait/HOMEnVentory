package servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Item;
import model.User;
import service.AccountService;
import service.ItemService;

/**
 *
 * @author 775262
 */
public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        User user = new AccountService().getUser(email);
        request.setAttribute("user", user);
        new ItemService().setAttributes(request, email);
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String action = request.getParameter("action");
        User user = new AccountService().getUser(email);
        request.setAttribute("user", user);
        ItemService is = new ItemService();


        if (action.equals("item-edit-modal")) {
            int itemID = Integer.parseInt(request.getParameter("itemID"));
            Item eitem = is.itemGet(itemID);
            request.setAttribute("edit_item", eitem);
            request.setAttribute("modal", true);
        }

        if (action.equals("item-add-modal")) {
            request.setAttribute("itemadd", true);
            request.setAttribute("modal", true);
        }

        if (action.equals("cancel-edit")) {
            request.setAttribute("modal", false);
        }

        if (action.equals("item-edit-save")) {
            String item_id = request.getParameter("editItemID");
            String item_name = request.getParameter("item-name");
            String price = request.getParameter("item-price");
            String category = request.getParameter("cate-option");
            if (item_id != null && !item_id.equals("") && item_name != null && !item_name.equals("") && price != null && !price.equals("") && category != null && !category.equals("")) {
                Item item = is.itemGet(Integer.parseInt(item_id));
                item.setCategory(Integer.parseInt(category));
                item.setPrice(Double.parseDouble(price));
                item.setItem_name(item_name);
                is.editItem(item);
            } else {
                request.setAttribute("modal", true);
                request.setAttribute("message", "Invalid, Please Try Again");
            }
        }

        if (action.equals("item-add-save")) {
            String item_name = request.getParameter("item-name");
            String price = request.getParameter("item-price");
            String category = request.getParameter("cate-option");
            if (item_name != null && !item_name.equals("") && price != null && !price.equals("") && category != null && !category.equals("")) {
                Item item = new Item();
                item.setCategory(Integer.parseInt(category));
                item.setPrice(Double.parseDouble(price));
                item.setItem_name(item_name);
                item.setOwner(email);
                is.addItem(item);
            } else {
                request.setAttribute("itemadd", true);
                request.setAttribute("modal", true);
                request.setAttribute("message", "Invalid, Please Try Again");
            }
        }

        if (action.equals("item-delete")) {
            String item_id = request.getParameter("itemID");
            if (item_id != null) {
                Item item = is.itemGet(Integer.parseInt(item_id));
                is.delete(item.getItem_id(), item.getOwner());
            }
        }

        is.setAttributes(request, email);
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }

}
