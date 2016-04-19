/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestaurantInfo;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import RestaurantInfo.Restaurant;
import org.primefaces.event.RateEvent;

/**
 *
 * @author gurvinder singh
 */
@ManagedBean
public class RatingView {

    private Integer rating1;
    private Restaurant rest = new Restaurant();

    /**
     *
     * @param rateEvent
     */
    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     *
     */
    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     *
     * @return
     */
    public Integer getRating1() {
        return rating1;
    }

    /**
     *
     * @param rating1
     */
    public void setRating1(Integer rating1) {
        this.rating1 = rating1;
        rest.setRATING(rating1.toString());

    }

}
