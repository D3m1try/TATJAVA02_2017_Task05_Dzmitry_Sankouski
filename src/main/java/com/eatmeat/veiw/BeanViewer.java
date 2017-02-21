package com.eatmeat.veiw;

import com.eatmeat.bean.Dish;
import com.eatmeat.bean.Menu;
import com.eatmeat.bean.SubMenu;

import javax.measure.unit.Unit;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

import static com.eatmeat.dao.util.Util.center;

/**
 * Created by Dzmitry_Sankouski on 20-Feb-17.
 */
public class BeanViewer {

    private static final int NAME_WIDTH = 30;
    private static final int DESC_WIDTH = 60;
    private static final int PORTION_WIDTH = 15;
    private static final int PRICE_WIDTH = 15;
    private static final int WIDTH = NAME_WIDTH + DESC_WIDTH + PORTION_WIDTH + PRICE_WIDTH;

    public static final String FORMATTER_FIRST_LEFT = "%1$-";
    public static final String S = "s ";
    private String WHITESPACE = " ";
    private String COLUMN_DELIM = "|";
    private String ROW_DELIM = "_";
    private String MENU_DELIM = "\n";
    private String DISH_DELIM = "-";
    private String PORTION_DELIM = "/";
    private String CARET = "\n";


    public String menuView(Menu menu) {
        if (menu == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        Formatter formatter = new Formatter(result);

        result.append(fullLineOf(ROW_DELIM)).append(center(menu.getName(), WIDTH)).append(fullLineOf(ROW_DELIM)).append(MENU_DELIM);

        for (SubMenu submenu :
                menu.getSubMenus()) {
            result.append(fullLineOf(ROW_DELIM))
                    .append(center(submenu.getName(), WIDTH))
                    .append(CARET).append(fullLineOf(ROW_DELIM));

            for (Dish dish :
                    submenu.getDishes()) {

                formatter.format(FORMATTER_FIRST_LEFT + NAME_WIDTH + S + COLUMN_DELIM, dish.getName());
                formatter.format(FORMATTER_FIRST_LEFT + DESC_WIDTH + S + COLUMN_DELIM, dish.getDecsription());


                StringBuilder portion = new StringBuilder();
                for (Map.Entry<Unit, List<Integer>> entry :
                        dish.getPortion().entrySet()) {
                    if (entry.getValue().isEmpty()) {
                        continue;
                    }

                    for (Integer value :
                            entry.getValue()) {
                        portion.append(value).append(PORTION_DELIM);
                    }
                    if (entry.getKey() == null) {// Unit == null if measure unit is not specified
                        portion.append(" шт.");
                    } else {
                        portion.append(entry.getKey().toString());
                    }
                    portion.append(WHITESPACE);
                }

                formatter.format(FORMATTER_FIRST_LEFT + PORTION_WIDTH + S + COLUMN_DELIM, portion);
                if (dish.getPrice() != null) {
                    formatter.format(FORMATTER_FIRST_LEFT + PRICE_WIDTH + S + COLUMN_DELIM, dish.getPrice());
                }
                result.append(CARET);

                //writing dish morphs
                int i = 0;
                for (Dish.DishMorph morph :
                        dish.getMorphs()) {
                    i++;
                    formatter.format(FORMATTER_FIRST_LEFT + NAME_WIDTH + S + COLUMN_DELIM, WHITESPACE);// inserting empty name, cause morph hasnt one
                    formatter.format(i + ". " + FORMATTER_FIRST_LEFT + DESC_WIDTH + S + COLUMN_DELIM, morph.getIngridient());
                    formatter.format(FORMATTER_FIRST_LEFT + PORTION_WIDTH + S + COLUMN_DELIM, WHITESPACE);
                    formatter.format(FORMATTER_FIRST_LEFT + PRICE_WIDTH + S + COLUMN_DELIM, morph.getPrice());
                    result.append(CARET);
                }


                result.append(fullLineOf(DISH_DELIM));
            }

            result.append(CARET);
        }


        return result.toString();
    }

    private String fullLineOf(String delim) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < WIDTH; i++) {
            res.append(delim);
        }
        res.append(CARET);
        return res.toString();
    }
}
