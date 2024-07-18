import React from 'react'
import { Button } from 'react-bootstrap'
import { NavLink } from 'react-router-dom';

function Logout() {

    const click = () => {
        localStorage.removeItem('jwt');
        localStorage.removeItem('role');
        localStorage.removeItem('user');
        console.log(localStorage);
        window.location.href = '/login';
    }
  return (
    <div>
        <Button as={NavLink} to="/" onClick={click}>Logout</Button>
    </div>
  )
}

export default Logout