import React from 'react';
import { useState } from "react";
import { useHistory } from "react-router-dom"
import axios from "axios";

const Login = () => {

	const [projectClientEmail, setProjectClientEmail] = useState("");
	const [projectClientPassword, setProjectClientPassword] = useState("");
	const history = useHistory();

	const handleSubmit = () => {
		axios.post("http://localhost:8080/validateProjectClient/",
			{
				projectClientEmail: projectClientEmail,
				projectClientPassword: projectClientPassword
			}).then(response => {

				console.log(response)
				history.push({
					pathname: '/ViewAllOSProjects',
					state: { projectClientDetails: response.data }
				});
			}).catch((error) => {
				history.push({
					pathname: '/ClientRegistrationFailure',
					state: {  registrationResponse: error.response.data }
				});
			});
	}

	return (

		<div className="login-form">
				<div className="input-container">
					<label>Username </label>
					<input
						type="email"
						name="projectClientEmail"
						placeholder="Project Client Email"
						value={projectClientEmail}
						onChange={(e) => { setProjectClientEmail(e.target.value) }}
						required
					/>
				</div>
				<div className="input-container">
					<label>Password </label>
					<input
						type="password"
						name="projectClientPassword"
						placeholder="Project Client Password"
						value={projectClientPassword}
						onChange={(e) => { setProjectClientPassword(e.target.value) }}
						required
					/>
				</div>
				<div className="button-container">
				<button type="submit" onClick={handleSubmit} >Login</button>
				</div>
		</div>
	);
}

export default Login;