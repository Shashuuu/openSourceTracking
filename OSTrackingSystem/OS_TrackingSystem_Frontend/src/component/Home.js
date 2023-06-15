import React from "react";
import { Link } from "react-router-dom";

function Home() {
 
    return (
        <div className="App">
             
            <header className="Homepage-header">

            <h1> Welcome to Open Source Tracking System</h1>
            <br></br>
            <span>Create new Account: <Link to="/register">Register</Link> </span>
            <br></br>
            <span>Already Have an Account: <Link to="/login">Login</Link> </span> 
            <br></br>
            </header>
        </div>
    );
}
export default Home;