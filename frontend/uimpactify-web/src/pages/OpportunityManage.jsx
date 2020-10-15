import React, { Component } from 'react';
import OpportunityEditForm from '../components/OpportunityEditForm.jsx';
import "../stylesheets/css/Opportunities.css";

function OpportunityManage(props) {
    const { title, description } = props.location.state

    return (
        <div className="pageContainer">
            <div className="shadow p-3 mb-5 bg-white rounded">
                <h3 className="pageHeader">Managing Opportunity</h3>
            </div>

            <OpportunityEditForm new={false} title={title} description={description}/>
        </div>
    );
}

export default OpportunityManage