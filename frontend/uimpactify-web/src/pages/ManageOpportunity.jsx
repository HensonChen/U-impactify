import React, { Component } from 'react';
import { updatePosting, getUser, deletePosting } from "../helpers/services/user-service";
import "../stylesheets/css/Opportunities.css";

class ManageOpportunity extends Component {
    constructor(props) {
        super(props);
        const { uid, type, pid, title, description } = props.location;
        this.state = {
                uid: uid,
                type: type,
                pid: pid,
                title: title,
                description: description
        }
    }

    handleSave = (event) => {
        event.preventDefault();
        const { pid, title, description, uid, type } = this.state;
        getUser(uid).then(
            (r) => {
                const socialInit = r.data.socialInit;
                updatePosting(pid, title, description, uid, type, socialInit.name);
            }
        );
        this.props.history.push("/myopportunities");
    }

    handleDelete = (event) => {
        event.preventDefault();
        deletePosting(this.state.pid);
        this.props.history.push("/myopportunities");
    }

    render() {
        const { title, description } = this.state;
        var isValid = title && description;

        return (
            <div className="pageContainer">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="pageHeader">Managing Opportunity</h3>
                </div>

                <form>
                    <div className="form-group">
                        <label htmlFor="title">Title</label>
                        <input 
                            type="text" 
                            className="form-control"
                            id="title" 
                            value={this.state.title}
                            onChange={(event) => this.setState({title: event.target.value})} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="type">Type</label>
                        <input 
                            type="text" 
                            className="form-control"
                            id="type" 
                            value={this.state.type}
                            disabled />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Description</label>
                        <textarea
                            type="text"
                            className="form-control"
                            id="description"
                            rows='10'
                            value={this.state.description}
                            onChange={(event) => this.setState({description: event.target.value})}/>
                    </div>
                    
                    <button type="submit" 
                            className="btn btn-primary formButtons" 
                            disabled={!isValid}
                            onClick={(event) => this.handleSave(event)}>
                        Save
                    </button>
                    <button type="submit" 
                            className="btn btn-secondary formButtons" 
                            onClick={(event) => this.handleDelete(event)}>
                        Remove
                    </button>
                </form>
            </div>
        );
    }
}

export default ManageOpportunity