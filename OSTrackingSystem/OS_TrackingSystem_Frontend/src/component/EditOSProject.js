import React from "react";
import { useState } from 'react';
import { useHistory } from "react-router-dom"
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";
import Swal from 'sweetalert2';
import axios from "axios";

export default function EditOSProject() {
	  const location = useLocation();
	  console.log(location.pathname);
	  console.log(location.state);
    // States for project
const [openSourceProjectId, setOpenSourceProjectId] = useState('');
const [openSourceProjectName, setOpenSourceProjectName] = useState('');
const [openSourceProjectLicense, setOpenSourceProjectLicense] = useState('');
const [openSourceProjectDescription, setOpenSourceProjectDescription] = useState('');
const [openSourceProjectURL, setOpenSourceProjectURL] = useState('');
const [projectClientDetails, setProjectClientDetails] = useState(location.state.projectClientDetails);
const [openSourceProjectDetails, setOpenSourceProjectDetails] = useState({...location.state.openSourceProjectDetails});
const openSourceProjectCreatedBy = openSourceProjectDetails.openSourceProjectCreatedBy;
const openSourceProjectUpdatedBy = openSourceProjectDetails.openSourceProjectUpdatedBy;

// States for checking the errors
const [submitted, setSubmitted] = useState(false);
const [error, setError] = useState(false);

// Handling the project URL change
const handleOpenSourceProjectDetails = (e) => {

	 const hold = {...openSourceProjectDetails}
	hold[e.target.name] = e.target.value;
	setOpenSourceProjectDetails(hold);
	setSubmitted(false);
};

// Handling the form submission
const handleSubmit = (e) => {
	e.preventDefault();
	if (openSourceProjectDetails.openSourceProjectName === '' || openSourceProjectDetails.openSourceProjectLicense === '' || openSourceProjectDetails.openSourceProjectDescription === '' || openSourceProjectDetails.openSourceProjectURL === '') {
	return Swal.fire({
			icon: 'error',
			title: 'Error',
			text: "All fields are required",
			showConfirmButton: true
		});	
	setError(true);
	} else {
	setSubmitted(true);
	setError(false);
	
	
	axios.put("http://localhost:8080/updateOSProject/",
			{	openSourceProjectId: openSourceProjectDetails.openSourceProjectId,
				openSourceProjectName: openSourceProjectDetails.openSourceProjectName,
				openSourceProjectLicense: openSourceProjectDetails.openSourceProjectLicense,
				openSourceProjectDescription: openSourceProjectDetails.openSourceProjectDescription,
				openSourceProjectURL: openSourceProjectDetails.openSourceProjectURL,
				openSourceProjectCreatedBy: openSourceProjectDetails.openSourceProjectCreatedBy,
				openSourceProjectUpdatedBy: openSourceProjectDetails.openSourceProjectUpdatedBy
			}).then(response => {

				console.log(response)
				history.push({
					pathname: '/ViewAllOSProjects',
					state: { projectClientDetails: location.state.projectClientDetails }
				});
			}).catch((error) => {
				console.log(error)
				history.push({
					pathname: '/BookFailure',
					state: {  registrationResponse: error.response.data }
				});
			});
}
};

let history = useHistory();

return (
	<div className="addOSProject">
		<Link to="/">Logout</Link>
	<div>
		<h1>Edit a Open Source Project</h1>
	</div>

        <label className="label">Project Name</label>
		<input name="openSourceProjectName" placeholder="Enter the Project name" onChange={handleOpenSourceProjectDetails} className="input"
		value={openSourceProjectDetails.openSourceProjectName} type="text" required="required"/>
		<br/>

        <label className="label">Project License</label>
		<input name="openSourceProjectLicense" placeholder="Enter the Project License" onChange={handleOpenSourceProjectDetails} className="input"
		value={openSourceProjectDetails.openSourceProjectLicense} type="text" required="required"/>
		<br/>

        <label className="label">Project Description</label>
		<input name="openSourceProjectDescription" placeholder="Enter the project Description" onChange={handleOpenSourceProjectDetails} className="input"
		value={openSourceProjectDetails.openSourceProjectDescription} type="text" required="required"/>
		<br/>

        <label className="label">project URL</label>
		<input name="openSourceProjectURL" placeholder="Enter the project URL" onChange={handleOpenSourceProjectDetails} className="input"
		value={openSourceProjectDetails.openSourceProjectURL} type="text" required="required"/>
		<br/>

		<div className="buttons-style">
        <button onClick={handleSubmit} className="btn" type="submit">
		Submit
		</button>
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