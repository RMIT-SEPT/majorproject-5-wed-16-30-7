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

    loginCustomer(username, password) {
        const loginURL = API_BASE_URL + '/login/' + username;
        return axios.post(loginURL, password, { headers: { 'Content-Type': 'text/plain' } });
    }

    editCustomer(customer, id) {
        const editURL = "http://localhost:8080/api/person/update/" + id;
        return axios.post(editURL, customer);
    }


}

export default new UserService()