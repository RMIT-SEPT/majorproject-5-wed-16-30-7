import axios from 'axios';

const API_BASE_URL = "http://localhost:8080/api/person";

class UserService {

    getCustomer() {
        return axios.get(API_BASE_URL);
    }

    createCustomer(customer) {
        return axios.post(API_BASE_URL, customer);
    }
}

export default new UserService()