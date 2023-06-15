import React from "react";
import { Link } from "react-router-dom";
import { useLocation, useHistory } from "react-router-dom";


export default function ClientRegistrationFailure() {
    const location = useLocation();
    const registrationResponse = location.state.registrationResponse;
    return (
        <div className="header">
            <h1>{registrationResponse}</h1>
            <h4>Please login with valid credentials!!!! <Link to='/login'>Login</Link></h4>
        </div>
    );
}