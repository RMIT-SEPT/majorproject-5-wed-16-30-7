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

    /*
    getSpecificCustomer(id){
        const persons = [];
        console.log(id);
        axios.get(API_BASE_URL)
                .then(res => {
                    persons = res.data;

                    
                })

       return persons[id - 1]
    }
    */
    //Register a customer
    createCustomer(customer) {
        return axios.post(API_BASE_URL + '/register', customer);

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


    getServices() {
        return axios.get("http://localhost:8080/api/service/all-services-distinct");
    }

    getProviderIDs(serviceName) {
        const providerIDs = "http://localhost:8080/api/service/get-provider-ids/" + serviceName;
        return axios.get(providerIDs);
    }

    getProviderName(id) {
        const providerName = "http://localhost:8080/api/provider/get-provider-by-id/" + id;
        return axios.get(providerName);
    }


    getCustomerOngoingBookings(user_id) {
        const ongoingBookings = "http://localhost:8080/api/booking/ongoing-bookings/" + user_id;
        return axios.get(ongoingBookings);
    }

}

export default new UserService()