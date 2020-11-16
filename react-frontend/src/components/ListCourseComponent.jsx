import React, { Component } from "react";
import CourseService from "../services/CourseService";

class ListCourseComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      courses: [],
    };
    this.addCourse = this.addCourse.bind(this);
    this.editCourse = this.editCourse.bind(this);
    this.deleteCourse = this.deleteCourse.bind(this);
  }

  deleteCourse(id) {
    console.log(' Entered deleteCourse function ', id)
    CourseService.deleteCourse(id).then((res) => {
      this.setState({
        courses: this.state.courses.filter((course) => course.id !== id),
      });
    });
  }
  viewCourse(id) {
    console.log(' Entered viewCourse function ', id)
    this.props.history.push(`/view-course/${id}`);
  }
  editCourse(id) {
    this.props.history.push(`/add-course/${id}`);
  }

  componentDidMount() {
    CourseService.getCourses().then((res) => {
      this.setState({ courses: res.data });
    });
  }

  addCourse() {
    console.log(' Entered addCourse function ')
    this.props.history.push("/add-course/_add");
  }

  render() {

    return (
      <div className="courseApp">
        <h2 className="text-center">Courses</h2>
        <div className="addCourse">
          <button
            id="addCourseButton"
            className="btn btn-primary"
            onClick={this.addCourse}
          >
            {" "}
            Add Course
          </button>
        </div>
        <br></br>
        <div className="courseLst" style={ { overflowY: 'scroll',  float: 'left',  position:'relative'} } >
          <table className="table table-striped table-bordered">
            <thead>
              <tr>
                <th> Title </th>
                <th> AuthorID </th>
                <th> Category</th>
                <th> Actions</th>
              </tr>
            </thead>
            <tbody className="courses" >
              {this.state.courses.map((course) => (
                <tr key={"course_details_" + course.id} className="course-detail" id="course_details">
                  <td> {course.title} </td>
                  <td> {course.authorId}</td>
                  <td> {course.category}</td>
                  <td>
                    <button
                      id="deleteCourseButton"
                      style={{ marginLeft: "10px" }}
                      onClick={() => this.deleteCourse(course.id)}
                      className="btn btn-danger"
                    >
                      Delete{" "}
                    </button>
                    <button
                      id="viewCourseButton"
                      style={{ marginLeft: "10px" }}
                      onClick={() => this.viewCourse(course.id)}
                      className="btn btn-info"
                    >
                      View{" "}
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default ListCourseComponent;
