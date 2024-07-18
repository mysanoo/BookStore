import React, { useState } from "react";
import {
    Button,
    Form,
    FormControl,
    FormGroup,
    Toast,
    ToastBody,
    ToastHeader,
} from "react-bootstrap";
import api from "../../api/api";
import axios from "axios";
import ApiService from "../../api/ApiService";
import { Link } from "react-router-dom";

function SignUp() {

    const [username, setUsername] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const [formData, setFormData] = useState({
        username: '',
        password: '',
        email: ''
    });

    const [error, setError] = useState('');
    console.log(formData);

    const handleSubmit = async (e) => {
        console.log("handle submit");
        e.preventDefault();

        let updated = {
            username: username,
            email: email,
            password: password
        }

        if (!username || !password || !email) {
            setError('Please fill in all fields');
            return;
        }

        try {
            ApiService.regiterUser(updated)
            .then(response => {
              localStorage.setItem('jwt', response.data.data);
              localStorage.setItem('role', 'ROLE_USER');
              window.location.href = '/'
            })
            .catch(error => {
              <Toast>
                <ToastHeader icon="primary"/>
                <ToastBody>
                  {error}
                </ToastBody>
              </Toast>
            })
        } catch (error) {
            setError('Invalid login credentials');
        }

        console.log(localStorage);
    };
  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-6 mt-5">
          <Form className="mt-5" onSubmit={handleSubmit}>
            <FormGroup className="mt-4">
              <FormControl type="text" placeholder="Username"  onChange={(e) => setUsername(e.target.value)}/>
            </FormGroup>
            <FormGroup className="mt-3">
              <FormControl type="email" placeholder="Email" onChange={(e) => setEmail(e.target.value)}/>
            </FormGroup>
            <FormGroup className="mt-3">
              <FormControl type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)}/>
            </FormGroup>
            <Button variant="secondary" type="submit" className="mt-3">login</Button>
          </Form>
        </div>
      </div>
      <Link to='/login' className="btn btn-primary">Sign In</Link>
    </div>
  );
}

export default SignUp;
