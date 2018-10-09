/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 759388
 */
public class ShoppingListServlet extends HttpServlet {
    
private static final String USERNAME_ATTR = "username";
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action != null && action.equals("logout")) {
            request.getSession().removeAttribute("name");
            request.getSession().invalidate();
        }
        
        String name = (String) request.getSession().getAttribute(USERNAME_ATTR);
        
        if (name != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null || action.isEmpty()) {
            request.setAttribute("message", "Missing 'action' parameter.");
            doGet(request, response);
            
            return;
        }
        
        if (request.getSession().getAttribute(USERNAME_ATTR) == null && !action.equals("register")) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);

            return;
        }
        
        ArrayList<String> items = (ArrayList<String>) request.getSession().getAttribute("items");
        
        if (items == null)
            items = new ArrayList<String>();
        
        switch (action) {
            case "register": {
                String name = request.getParameter(USERNAME_ATTR);
            
                if (name == null || name.trim().isEmpty()) {
                    request.setAttribute("message", "Name is missing or is empty.");
                    getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                    
                    return;
                } else {
                    request.getSession().setAttribute(USERNAME_ATTR, request.getParameter(USERNAME_ATTR));
                    
                }
            
                break;
            }
            case "add": {
                String item = request.getParameter("item");
                
                if (item == null || item.trim().isEmpty()) {
                    request.setAttribute("message", "Item cannot be missing or empty.");
                } else {
                    items.add(item);
                    request.setAttribute("info", "Add item to list successfully.");
                }
                
                break;
            }
            
            case "delete": {
                String itemToDelete = request.getParameter("item");
                
                if (itemToDelete == null || itemToDelete.trim().isEmpty()) {
                    request.setAttribute("message", "Item is not selected.");
                } else {
                    int deleted = 0;
                    
                    for (int i = 0; i < items.size(); i++) {
                        String item = items.get(i);
                        
                        if (item.equals(itemToDelete)) {
                            items.remove(i);
                            deleted++;
                        }
                            
                    }
                    
                    if (deleted > 0)
                        request.setAttribute("info", "Deleted " + deleted + " item(s) from list successfully.");
                    else
                        request.setAttribute("message", "No items found to be deleted.");
                }
                
                break;
            }
        }
        
        request.getSession().setAttribute("items", items);
        request.setAttribute("items", items);
        
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
    }
}
