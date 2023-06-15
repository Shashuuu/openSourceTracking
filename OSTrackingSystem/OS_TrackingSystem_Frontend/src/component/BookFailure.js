import React from "react";
import { Link } from "react-router-dom";
import { useLocation, useHistory } from "react-router-dom";


export default function BookFailure() {
    const location = useLocation();
    const history = useHistory();
    const registrationResponse = location.state.registrationResponse;
    return (
        <div className="header">
            <h1>{registrationResponse}</h1>
            <button id="back_button" onClick={()=>{
         	history.push({
               pathname: '/ViewAllOSProjects',
               state: { projectClientDetails: location.state.projectClientDetails }
            });
      }}>Back</button>
        </div>
    );
}