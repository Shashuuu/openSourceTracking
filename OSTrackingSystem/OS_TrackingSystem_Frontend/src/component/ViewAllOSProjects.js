import React, { useEffect } from "react";
import { useState } from 'react';
import { useHistory, useLocation } from "react-router-dom"
import axios from "axios";
import { MDBTable, MDBTableBody, MDBTableHead } from 'mdbreact';


export default function ViewAllOSProjects() {
	const location = useLocation();
	const history = useHistory();
	const [currentProjectState, setCurrentProjectState] = useState('');
	const jsonData = location.state.projectClientDetails;

	const handleLogout = () => {
		history.push("/")
	}

	const handleProjectDetails = (project) => {
		const currentOpenSourceProjectDetails = {
			"openSourceProjectId": project.openSourceProjectId,
			"openSourceProjectCreateDate": project.openSourceProjectCreateDate,
			"openSourceProjectUpdatedDate": project.openSourceProjectUpdatedDate,
			"openSourceProjectName": project.openSourceProjectName,
			"openSourceProjectLicense": project.openSourceProjectLicense,
			"openSourceProjectDescription": project.openSourceProjectDescription,
			"openSourceProjectURL": project.openSourceProjectURL,
			"openSourceProjectState": project.openSourceProjectState,
			"openSourceProjectCreatedBy": project.openSourceProjectCreatedBy,
			"openSourceProjectCreateDate": project.openSourceProjectCreateDate,
			"openSourceProjectUpdatedBy": project.openSourceProjectUpdatedBy,
			"openSourceProjectUpdateDate": project.openSourceProjectUpdateDate
		};
		history.push({
			pathname: '/ViewOSProject',
			state: { openSourceproject: currentOpenSourceProjectDetails, projectClientDetails: jsonData }
		});
	}

	const handleEdit = (project) => {
		history.push({
			pathname: '/EditOSProject',
			state: { projectClientDetails: jsonData, openSourceProjectDetails: project }
		});

	}
	
	const [projects, setProjects] = useState([]);
	useEffect(() => {
		getLisOfProjects({});
	}, []);

	const handleAddProject = () => {
		history.push({
			pathname: '/AddOSProject',
			state: { projectClientDetails: jsonData }
		});

	}

	async function getLisOfProjects() {
		try {
			const projects = await axios.get("http://localhost:8080/getOSProjectsList/")
			projects.data.forEach(element => {
				console.log(element);
				element.canChange = true;
			});
			setProjects(projects.data);

		}
		catch (error) {
			console.log("Errored while getting list of projects")
		}
	}
	const updateProjectState = (project) => {
		if (currentProjectState === 'Approved' || currentProjectState === 'Denied') {

			axios.put("http://localhost:8080/updateOSProject",
				{
					openSourceProjectId: project.openSourceProjectId,
					openSourceProjectName: project.openSourceProjectName,
					openSourceProjectLicense: project.openSourceProjectLicense,
					openSourceProjectDescription: project.openSourceProjectDescription,
					openSourceProjectURL: project.openSourceProjectURL,
					openSourceProjectUpdatedBy: jsonData.projectClientId,
					openSourceProjectState: currentProjectState
				}).then(response => {
					console.log(response);
					const hold = [...projects];
					hold.forEach(currentOpenSourceProject => {
						if (currentOpenSourceProject.openSourceProjectId == project.openSourceProjectId){
							currentOpenSourceProject.openSourceProjectState = currentProjectState;
						} 
					})
					setProjects(hold)

				}).catch((error) => {
					console.log(error)
				});

		} else if (currentProjectState === 'Cancel') {
			axios.delete("http://localhost:8080/deleteOSProject",
				{
					data: {
						openSourceProjectId: project.openSourceProjectId,
						openSourceProjectName: project.openSourceProjectName,
						openSourceProjectLicense: project.openSourceProjectLicense,
						openSourceProjectDescription: project.openSourceProjectDescription,
						openSourceProjectURL: project.openSourceProjectURL
					}

				}).then(response => {
					const hold = [];
					projects.forEach(currentOpenSourceProject => {
						if (currentOpenSourceProject.openSourceProjectId !== project.openSourceProjectId)
							hold.push(currentOpenSourceProject)
					})
					setProjects(hold)
				}).catch((error) => {
					console.log(error)
				});
		}
		setCurrentProjectState('');
	}
	const projectStateDropDown = (json) => {
		const options = [];
		const key = json.openSourceProjectState;
		options.push(<option>InProcess</option>);
		if (key == "Approved") {
			options.push(<option key="Approved" selected="selected">Approved</option>);
		} else if (json.openSourceProjectCreatedBy !== jsonData.projectClientId && jsonData.projectClientRole === 'Approver') {

			options.push(<option>Approved</option>);
		}
		if (key == "Denied") {
			options.push(<option selected="selected">Denied</option>);
		} else if (json.openSourceProjectCreatedBy !== jsonData.projectClientId && jsonData.projectClientRole === 'Approver') {
			options.push(<option>Denied</option>);
		}


		if (json.openSourceProjectCreatedBy === jsonData.projectClientId) {
			options.push(<option>Cancel</option>);

		}

		return (
			<select onChange={(e) => {
				console.log(e.target.value);
				setCurrentProjectState(e.target.value);
				console.log(currentProjectState);
			}} disabled={json.openSourceProjectState === "Approved" || json.openSourceProjectState === "Denied"}>
				{options}
			</select>
		);
	}

	return (
		<div className="contain-table">
			<br />
			<h1>Open Source Tracking System</h1>
			<button className='logout round-button' onClick={handleLogout}>Logout</button>
			<br></br>
			<br></br>
			<br />
			<span>Click here to Add a new open Source Project: </span><button className='round-button' onClick={handleAddProject}>Add Project</button>
			<hr />
			<h2>List of Open Source Projects</h2>

			<MDBTable hover>
			<MDBTableHead>
				
					<tr>
						<th>Project Name</th>
						<th>Project State</th>
						<th colSpan={2} className="text-center">Actions</th>
					</tr>
				</MDBTableHead>
				<MDBTableBody>
					{
						projects.map((project, i) => {
							return (
								<tr key={i}>
									<td>{project.openSourceProjectName}</td>
									<td>{projectStateDropDown(project)}</td>
									<td className="text-right">
										<div className="project-actions">
											<button onClick={() => handleProjectDetails(project)}>View</button>
											<button disabled={project.openSourceProjectState === "Approved" || project.openSourceProjectState === "Denied"} className="Button muted-button" onClick={() => updateProjectState(project)}>Update Project State</button>
											<button disabled={jsonData.projectClientId !== project.openSourceProjectCreatedBy || project.openSourceProjectState === "Approved" || project.openSourceProjectState === "Denied"} className="Button muted-button" onClick={() => handleEdit(project)}>Edit</button>
										</div>
									</td>
								</tr>
							)
						})
					}
				</MDBTableBody>
			</MDBTable>

		</div>
	);

}