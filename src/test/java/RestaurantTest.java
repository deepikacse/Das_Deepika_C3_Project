import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;



class RestaurantTest {
    Restaurant restaurant;

    //REFACTORING ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void createRestaurant() {

        {
            LocalTime openingTime = LocalTime.parse("10:30:00");
            LocalTime closingTime = LocalTime.parse("22:00:00");
            restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
            restaurant.addToMenu("Sweet corn soup", 119);
            restaurant.addToMenu("Vegetable lasagne", 269);
        }
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    //Positive Test case
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {

        //ACT
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        //Arrange
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("11:30:00"));
        //Assert
        assertTrue(spyRestaurant.isRestaurantOpen());


    }

    @Test
    //Negative Test case
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        //ACT
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        //Arrange
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("23:00:00"));
        //Assert
        assertFalse(spyRestaurant.isRestaurantOpen());


    }


    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }


//<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<Total Order>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//Added failing  test case for implementing feature for calculating order value ,implemented the method and tested
    @Test
    public void selecting_item_from_menu_should_return_order_cost() {
        int orderTotal;
        List<String> selectedItems = Arrays.asList("Sweet corn soup", "Vegetable lasagne");
        orderTotal = restaurant.calculateOrderTotal(selectedItems);
        assertEquals(orderTotal,388);
    }
}
//<<<<<<<<<<<<<<<<<<<<<<<Total Order>>>>>>>>>>>>>>>>>>>>>>>>>>>>>