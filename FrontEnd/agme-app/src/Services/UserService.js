import axios from 'axios';

const API_REGISTER = "http://localhost:8080/api/person/register";

class UserService {

    getCustomer() {
        return axios.get(API_REGISTER);
    }

    createCustomer(customer) {
        return axios.post(API_REGISTER, customer);
    }

    
}

export default new UserService()