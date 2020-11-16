import React, { Component } from "react";
import CourseService from "../services/CourseService";

class ViewCourseComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      course: {},
    };
  }

  componentDidMount() {
    CourseService.getCourseById(this.state.id).then((res) => {
      this.setState({ course: res.data });
    });
  }

  render() {
    return (
      <div>
        <br></br>
        <div className="card col-md-6 offset-md-3">
          <h3 className="text-center"> View Course Details</h3>
          <div className="card-body">
            <div className="row">
              <label> Title: </label>
              <div> {this.state.course.title}</div>
            </div>
            <div className="row">
              <label> AuthorId: </label>
              <div> {this.state.course.authorId}</div>
            </div>
            <div className="row">
              <label> Category: </label>
              <div> {this.state.course.category}</div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default ViewCourseComponent;
