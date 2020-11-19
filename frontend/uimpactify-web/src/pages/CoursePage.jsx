import React, { Component } from 'react';
import CourseSessionRoom from '../components/CourseSessionRoom';
import "../stylesheets/css/Courses.css";

class CoursePage extends Component {
    constructor(props) {
        super(props);
        const { cid, title, description, instructor } = props.location.state;
        this.state = {
            cid: cid,
            title: title,
            description: description,
            instructor: instructor
        }
    }

    handleSwitch(tab) {
        var i;
        var tabcontents = document.getElementsByClassName("courses-page-content");
        for (i = 0; i < tabcontents.length; i++) {
            tabcontents[i].style.display = "none";
        }
        var tablinks = document.getElementsByClassName("courses-page-sidebar-tab");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active-tab", "");
        }
        document.getElementById(tab + "-tab-content").style.display = "";
        document.getElementById(tab + "-tab").className += " active-tab";
    }

    render() {
        const { cid, title, description, instructor } = this.state;

        return(
            <div className="courses-page-container">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="courses-page-header">My Courses</h3>
                </div>

                <div className="courses-page-grid">
                    <div className="courses-page-sidebar">
                        <a id="home-tab" className="courses-page-sidebar-tab active-tab"
                            onClick={() => this.handleSwitch("home")} href="#">
                            Home
                        </a>
                        <a id="class-sessions-tab" className="courses-page-sidebar-tab"
                            onClick={() => this.handleSwitch("class-sessions")} href="#">
                            Class Sessions
                        </a>
                        <a id="assignments-tab" className="courses-page-sidebar-tab"
                            onClick={() => this.handleSwitch("assignments")} href="#">
                            Assignments
                        </a>
                        <a id="quizzes-tab" className="courses-page-sidebar-tab"
                            onClick={() => this.handleSwitch("quizzes")} href="#">
                            Quizzes
                        </a>
                    </div>

                    <div id="home-tab-content" className="courses-page-content default">
                        <h3>{title}</h3>
                        <p>Instructed by {instructor}</p>
                        <p>{description}</p>
                    </div>

                    <div id="class-sessions-tab-content" className="courses-page-content"
                        style={{display: "none"}}>
                        <CourseSessionRoom uid={this.props.uid}></CourseSessionRoom>
                    </div>
                    
                    <div id="assignments-tab-content" className="courses-page-content"
                        style={{display: "none"}}>
                        Add content for "Assignments" here
                    </div>
                    
                    <div id="quizzes-tab-content" className="courses-page-content"
                        style={{display: "none"}}>
                        Add content for "Quizzes" here
                    </div>
                </div>

            </div>
        );
    }
}

export default CoursePage;