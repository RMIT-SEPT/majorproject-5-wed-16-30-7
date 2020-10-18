import axios from 'axios';

const API_BASE_URL = "http://localhost:8080/api/person";

class UserService {

    constructor(props) {

        this.state = {
            id: 0
        }
    }

    getCustomer() {
        return axios.get(API_BASE_URL);
    }

    createCustomer(customer) {
        return axios.post(API_BASE_URL + '/register/', customer);

    }

    //Login any user
    loginCustomer(username, password) {
        const loginURL = API_BASE_URL + '/login/' + username;
        return axios.post(loginURL, password, { headers: { 'Content-Type': 'text/plain' } });
    }

    //Edit customer details
    editCustomer(customer, id) {
        const editURL = "http://localhost:8080/api/person/update/" + id;
        return axios.post(editURL, customer);
    }

    //Return a list of distinct services
    getServices() {
        return axios.get("http://localhost:8080/api/service/all-services-distinct");
    }

    //Get all providers corresponding to a service
    getProviders(serviceName) {
        const providerIDs = "http://localhost:8080/api/service/get-providers/" + serviceName;
        return axios.get(providerIDs);
    }

    //Get name of a provider given the id
    getProviderName(id) {
        const providerName = "http://localhost:8080/api/provider/get-provider-by-id/" + id;
        return axios.get(providerName);
    }

    //Get customers all ongoing bookings
    getCustomerOngoingBookings(user_id) {
        const ongoingBookings = "http://localhost:8080/api/booking/ongoing-bookings/" + user_id;
        return axios.get(ongoingBookings);
    }

    //Create a new booking and post data to backend
    makeNewBooking(booking) {
        const createBooking = "http://localhost:8080/api/booking/create-booking";
        return axios.post(createBooking, booking);
    }

    //Get customers all past bookings (completed/cancelled)
    getCustomerPastBooking(user_id) {
        const pastBookings = "http://localhost:8080/api/booking/booking-history/" + user_id;
        return axios.get(pastBookings);
    }

}

export default new UserService()