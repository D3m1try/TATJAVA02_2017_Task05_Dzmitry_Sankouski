package com.eatmeat.dao.impl;

import com.eatmeat.bean.Dish;
import com.eatmeat.bean.Menu;
import com.eatmeat.bean.SubMenu;
import com.eatmeat.dao.GenericDAO;
import com.eatmeat.dao.exception.DAOException;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.measure.unit.SI;
import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.eatmeat.dao.util.Util.tryParse;
import static com.eatmeat.dao.util.XmlUtil.asList;
import static com.eatmeat.dao.util.XmlUtil.clean;


/**
 * Created by Dzmitry_Sankouski on 17-Feb-17.
 */
public class DOMDAO implements GenericDAO<Menu, String> {
    private String DOCUMENT = "C:\\Users\\Dzmitry_Sankouski\\IdeaProjects\\TATJAVA02_2017_Task05_Dzmitry_Sankouski\\src\\main\\resources\\menu.xml";

    public String create(Menu newInstance) throws DAOException {
        throw new DAOException(new OperationNotSupportedException("not implemented"));
    }

    public Menu read(String id) throws DAOException {

        DOMParser parser = new DOMParser();
        Element rootElement = null;
        Document doc;
        Menu menu;
        Dish dish;
        SubMenu subMenu;


        try {
            parser.parse(DOCUMENT);
            doc = parser.getDocument();
            rootElement = doc.getDocumentElement();
            clean(rootElement);//erasing whitespaces & trimming values

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        menu = new Menu();
        menu.setName(rootElement.getAttribute("name"));

        //writing menu
        List<Node> subMenus = asList(rootElement.getChildNodes());
        for (Node subMenuNode :
                subMenus) {//menu iterating

            subMenu = new SubMenu();
            subMenu.setName(subMenuNode.getAttributes().getNamedItem("name").getTextContent());

            //writing sub menu
            List<Node> dishNodes = asList(subMenuNode.getChildNodes());
            for (Node dishNode :
                    dishNodes) {//submenu iterating
                //writing dish
                dish = new Dish();


                NodeList dishChildren = dishNode.getChildNodes();
                dishChildren.item(0).getTextContent();
                dish.setPhoto(URI.create(dishChildren.item(0).getTextContent()));
                dish.setName(dishChildren.item(1).getTextContent());
                dish.setDescription(dishChildren.item(2).getTextContent());

                //writing portions
                List<Integer> masses = new ArrayList<>();
                List<Integer> counts = new ArrayList<>();
                for (Node portionValue :
                        asList(dishChildren.item(3).getChildNodes())) {
                    if (portionValue.getNodeName().equalsIgnoreCase("mass")) {
                        masses.add(new Integer(tryParse(portionValue.getTextContent())));
                    }
                    if (portionValue.getNodeName().equalsIgnoreCase("count")) {
                        counts.add(new Integer(tryParse(portionValue.getTextContent())));
                    }
                }
                dish.getPortion().put(SI.GRAM, masses);
                dish.getPortion().put(null, counts);

                // writing morphs
                if (dishChildren.item(4).getNodeName().equalsIgnoreCase("morphs")) {
                    for (Node morph :
                            asList(dishChildren.item(4).getChildNodes())) { // iterating morphs

                        dish.getMorphs()//adding new dishMorph to dishmorphs
                                .add(dish.new DishMorph(// creating new dishmorph
                                                morph.getFirstChild().getTextContent(),
                                        tryParse(morph.getLastChild().getTextContent())
                                        )
                                );

                    }
                }

                //writing price
                if (dishChildren.item(4).getNodeName().equalsIgnoreCase("price")) {
                    Integer price = tryParse(dishChildren.item(4).getTextContent());
                    dish.setPrice(price);
                }

                subMenu.getDishes().add(dish);
            }
            menu.getSubMenus().add(subMenu);
        }


        return menu;
    }

    public void update(Menu transientObject) throws DAOException {
        throw new DAOException(new OperationNotSupportedException("not implemented"));
    }

    public void delete(Menu persistentObject) throws DAOException {
        throw new DAOException(new OperationNotSupportedException("not implemented"));
    }


    public List<Dish> find(String fieldName, String value, boolean isPureSearch) throws DAOException {
        throw new DAOException(new OperationNotSupportedException("not implemented"));
    }
}
