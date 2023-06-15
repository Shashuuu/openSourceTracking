import React from "react";
import { useLocation, useHistory } from "react-router-dom";

export default function ViewOSProject(){
   const location = useLocation();
   const history = useHistory();
  const openSourceproject = location.state.openSourceproject;
  const projectClientName = location.state.projectClientDetails.projectClientName;
    return (
      <div id="project_details_page" className="project_details_page">
      <h1>Open Source Project Information</h1>
      <h2> Project Created By:  {projectClientName}</h2>
      <div id="projectDetails">
      <p>
         <span className="projectLabel">Open Source Project Name: </span>
         <span className="projectValue">{openSourceproject.openSourceProjectName}</span>
      </p>
      <p>
         <span className="projectLabel">Open Source Project License: </span>
         <span className="projectValue">{openSourceproject.openSourceProjectLicense}</span>
      </p>
      <p>
         <span className="projectLabel">Open Source Project Details: </span>
         <span className="projectValue">{openSourceproject.openSourceProjectDescription}</span>
      </p>
      <p>
         <span className="projectLabel">Open Source Project URL: </span>
         <span className="projectValue">{openSourceproject.openSourceProjectURL}</span>
      </p>
      <p>
         <span className="projectLabel">Open Source Project State: </span>
         <span className="projectValue">{openSourceproject.openSourceProjectState}</span>
      </p>
      <p>
         <span className="projectLabel">Open Source Project Created By: </span>
         <span className="projectValue">{openSourceproject.openSourceProjectCreatedBy}</span>
      </p>
      <p>
         <span className="projectLabel">Open Source Project Create date: </span>
         <span className="projectValue">{openSourceproject.openSourceProjectCreateDate}</span>
      </p>
      <p>
         <span className="projectLabel">Open Source Project Updated by: </span>
         <span className="projectValue">{openSourceproject.openSourceProjectUpdatedBy}</span>
      </p>
      <p>
         <span className="projectLabel">Open Source Project Update date: </span>
         <span className="projectValue">{openSourceproject.openSourceProjectUpdateDate}</span>
      </p>

      <button id="back_button" onClick={()=>{
         	history.push({
               pathname: '/ViewAllOSProjects',
               state: { projectClientDetails: location.state.projectClientDetails }
            });
      }}>Back</button>
      </div>
  
      </div>
    );
  }