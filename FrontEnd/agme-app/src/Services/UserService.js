import axios from 'axios';

const API_BASE_URL = "http://localhost:8080/api/person/";

class UserService {

    constructor(props) {

        this.state = {
            id: 0
        }
    }

    getCustomer() {
        return axios.get(API_REGISTER);
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

    createCustomer(customer) {
        const oldid = this.state.id;
        this.state.id += 1;
        return axios.post(API_BASE_URL, customer);

    }

    editCustomer(customer, id) {
        const editURL = "http://localhost:8080/api/person/update/" + id;
        return axios.post(editURL, customer);

    }


}

export default new UserService()