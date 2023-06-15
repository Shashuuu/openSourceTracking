import React from "react";
import { Link } from "react-router-dom";
import { useState } from 'react';
import axios from "axios";
import { useHistory } from "react-router-dom"

export default function Register() {

	// States for registration
	const [projectClientName, setProjectClientName] = useState('');
	const [projectClientEmail, setProjectClientEmail] = useState('');
	const [projectClientRole, setProjectClientRole] = useState('');
	const [projectClientPassword, setProjectClientPassword] = useState('');

	// States for checking the errors
	const [submitted, setSubmitted] = useState(false);
	const [error, setError] = useState(false);

	// Handling the form submission
	const handleSubmit = (e) => {
		e.preventDefault();
		if (projectClientName === '' || projectClientEmail === '' || projectClientRole === '' || projectClientPassword === '') {
			setError(true);
		} else {
			setSubmitted(true);
			setError(false);
			axios.post("http://localhost:8080/registerProjectClient/",
			{
				projectClientEmail: projectClientEmail,
				projectClientName: projectClientName,
				projectClientRole: projectClientRole,
				projectClientPassword: projectClientPassword
			}).then(response => {
				console.log("New client registration successful")
					history.push({
						pathname: '/ClientRegistrationFailure',
						state: {  registrationResponse: response.data }
					});
				
			}).catch((error) => {
				if (error.response.data === "ProjectClient already exists") {
					console.log("User already registered")
					history.push({
						pathname: '/ClientRegistrationFailure',
						state: {  registrationResponse: error.response.data }
					});
				}
				else {
					history.push({
						pathname: '/ClientRegistrationFailure',
						state: {  registrationResponse: error.response.data }
					});
				}
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
		<div className="register">
			<div>
				<h2>Project Client Registration</h2>
			</div>

			{/* Calling to the methods */}
			<div className="messages">
				{errorMessage()}
				{successMessage()}
			</div>

			{/* Labels and inputs for register data */}
			<label className="label">Project Client Name: </label>
			<input placeholder="Project Client Name" 
			onChange={(event) => { setProjectClientName(event.target.value); } } className="input"
				value={projectClientName} type="text" required="required" />
			<br />
			<br />

			<label className="label">Project Client Email: </label>
			<input placeholder="Project Client Email" 
			onChange={(event) => { setProjectClientEmail(event.target.value); } } className="input"
				value={projectClientEmail} type="email" required="required" />
			<br />
			<br />

			<label className="label">Project Client Role: </label>
			<select onChange={(event) => { setProjectClientRole(event.target.value); } } className="input"
			defaultValue="Select Client Role">
				<option defaultValue>Select Role</option>
				<option value="Requester">Requester</option>
				<option value="Approver">Approver</option>
			</select>
			<br />
			<br />

			<label className="label">Project Client Password: </label>
			<input placeholder="Project Client password" 
			onChange={(event) => { setProjectClientPassword(event.target.value); } } className="input"
				value={projectClientPassword} type="text" required="required" />
			<br />
			<br />

			<button onClick={handleSubmit} className="btn" type="submit">
				Register
			</button>
			<div>
				<h4>Already have an account ? <Link to='/login'>please login</Link></h4>
			</div>
		</div>
	);
}