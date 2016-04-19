package RestaurantInfo;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Saurabh
 */
@ManagedBean
@SessionScoped
public class PageController implements Serializable {

    /**
     *
     * @return
     */
    public String moveToPage() {
        return "each"; //outcome
    }
}
