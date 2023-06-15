import React from "react";
import { useState } from 'react';
import { useHistory } from "react-router-dom"
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";
import Swal from 'sweetalert2';
import axios from "axios";

export default function AddOSProject() {
	const location = useLocation();
	console.log(location.pathname);
	console.log(location.state);
	// States for project
	const [openSourceProjectName, setOpenSourceProjectName] = useState('');
	const [openSourceProjectLicense, setOpenSourceProjectLicense] = useState('');
	const [openSourceProjectDescription, setOpenSourceProjectDescription] = useState('');
	const [openSourceProjectURL, setOpenSourceProjectURL] = useState('');

	const openSourceProjectCreatedBy = location.state.projectClientDetails.projectClientId;
	const openSourceProjectUpdatedBy = location.state.projectClientDetails.projectClientId;

	// States for checking the errors
	const [submitted, setSubmitted] = useState(false);
	const [error, setError] = useState(false);


	// Handling the Project name change
	const handleOpenSourceProjectName = (e) => {
		setOpenSourceProjectName(e.target.value);
		setSubmitted(false);
	};

	// Handling the project license change
	const handleOpenSourceProjectLicense = (e) => {
		setOpenSourceProjectLicense(e.target.value);
		setSubmitted(false);
	};

	// Handling the project description change
	const handleOpenSourceProjectDescription = (e) => {
		setOpenSourceProjectDescription(e.target.value);
		setSubmitted(false);
	};

	// Handling the project URL change
	const handleOpenSourceProjectURL = (e) => {
		setOpenSourceProjectURL(e.target.value);
		setSubmitted(false);
	};

	// Handling the form submission
	const handleSubmit = (e) => {
		e.preventDefault();
		if (openSourceProjectName === '' || openSourceProjectLicense === '' || openSourceProjectDescription === '' || openSourceProjectURL === '') {
			return Swal.fire({
				icon: 'error',
				title: 'Error',
				text: "All fields are required",
				allowOutsideClick: false,
				showConfirmButton: true
			});
			setError(true);
		} else {
			setSubmitted(true);
			setError(false);

			axios.post("http://localhost:8080/addOSProject/",
			{
				openSourceProjectName: openSourceProjectName,
				openSourceProjectLicense: openSourceProjectLicense,
				openSourceProjectDescription: openSourceProjectDescription,
				openSourceProjectURL: openSourceProjectURL,
				openSourceProjectCreatedBy: openSourceProjectCreatedBy,
				openSourceProjectUpdatedBy: openSourceProjectUpdatedBy
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
					state: {  registrationResponse: error.response.data, projectClientDetails: location.state.projectClientDetails }
				});
			});
		}
	};


	let history = useHistory();

	// Showing success message
	const successMessage = () => {
		return (
			<div
				className="success"
				style={{
					display: submitted ? '' : 'none',
				}}>
				<h1>{openSourceProjectName} created successfully!!</h1>
			</div>
		);
	};

	// Showing error message if error is true
	const errorMessage = () => {

		return (
			<div
				className="error"
				style={{
					display: error ? '' : 'none',
				}}>
				<h1>Please enter all the fields</h1>
			</div>
		);
	};

	return (
		<div className="addOSProject">
			<Link to="/">Logout</Link>
			<div>
				<h1>Add a Open Source Project</h1>
			</div>

			{ /*Labels and inputs for add project*/}

			<label className="label">Open Source Project Name: </label>
			<input placeholder="Enter the Project name" onChange={handleOpenSourceProjectName} className="input"
				value={openSourceProjectName} type="text" required="required" />
			<br />

			<label className="label">Open Source Project License: </label>
			<input placeholder="Enter the Project License" onChange={handleOpenSourceProjectLicense} className="input"
				value={openSourceProjectLicense} type="text" required="required" />
			<br />

			<label className="label">Open Source Project Description: </label>
			<input placeholder="Enter the project Description" onChange={handleOpenSourceProjectDescription} className="input"
				value={openSourceProjectDescription} type="text" required="required" />
			<br />

			<label className="label">Open Source project URL: </label>
			<input placeholder="Enter the project URL" onChange={handleOpenSourceProjectURL} className="input"
				value={openSourceProjectURL} type="text" required="required" />
			<br />
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