import React from 'react'
import { useState } from 'react';
import axios from 'axios';
import ApiService from '../../api/ApiService';
import { Link } from 'react-router-dom';


function SignIn() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");


    const handleSubmit = async (e) => {
        e.preventDefault();

        let updated = {
            username: username,
            password: password
        }

        try {
            ApiService.loginUser(updated)
            .then(response => {
                localStorage.setItem("jwt", response.data.message);
                localStorage.setItem("role", response.data.data.userRole);
                localStorage.setItem("user", JSON.stringify(response.data.data));
                if(response.data.data.userRole == 'ROLE_USER'){
                    window.location.href = '/';
                }else window.location.href = "/";
            })
            .catch(error => {
                console.log(error);
            })
        } catch (error) {
            console.error('There was an error!', error);
        }
    };


    const formStyle = {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        gap: '10px',
        maxWidth: '300px',
        margin: 'auto'
    };

    const inputStyle = {
        padding: '10px',
        fontSize: '16px',
        width: '100%'
    };

    const buttonStyle = {
        padding: '10px',
        fontSize: '16px',
        width: '100%',
        cursor: 'pointer'
    };
  return (
    <div>
        <div>
            <h1>Sign In</h1>
            <form onSubmit={handleSubmit} style={formStyle}>
                <div>
                    <label>Username:</label>
                    <input
                        type="text"
                        name="username"
                        onChange={(e) => setUsername(e.target.value)}
                        style={inputStyle}
                        required
                    />
                </div>
                <div>
                    <label>Password:</label>
                    <input
                        type="password"
                        name="password"
                        onChange={(e) => setPassword(e.target.value)}
                        style={inputStyle}
                        required
                    />
                </div>
                <button type="submit" style={buttonStyle}>Sign In</button>
            </form>
        </div>
        <Link to='/register' className='btn btn-primary'>Sign Up</Link>
    </div>
  )
}

export default SignIn