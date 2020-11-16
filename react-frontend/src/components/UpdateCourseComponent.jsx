import React, { Component } from "react";
import CourseService from "../services/CourseService";

class UpdateCourseComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      title: "",
      authorId: "",
      category: "",
    };
    this.changeTitleHandler = this.changeTitleHandler.bind(this);
    this.changeAuthorIdHandler = this.changeAuthorIdHandler.bind(this);
    this.updateCourse = this.updateCourse.bind(this);
  }

  componentDidMount() {
    CourseService.getCourseById(this.state.id).then((res) => {
      let course = res.data;
      this.setState({
        title: course.title,
        authorId: course.authorId,
        category: course.category,
      });
    });
  }

  updateCourse = (e) => {
    e.preventDefault();
    let course = {
      title: this.state.title,
      authorId: this.state.authorId,
      category: this.state.category,
    };
    console.log("course => " + JSON.stringify(course));
    console.log("id => " + JSON.stringify(this.state.id));
    CourseService.updateCourse(course, this.state.id).then((res) => {
      this.props.history.push("/courses");
    });
  };

  changeTitleHandler = (event) => {
    this.setState({ title: event.target.value });
  };

  changeAuthorIdHandler = (event) => {
    this.setState({ authorId: event.target.value });
  };

  changeCategoryHandler = (event) => {
    this.setState({ category: event.target.value });
  };

  cancel() {
    this.props.history.push("/courses");
  }

  render() {
    return (
      <div>
        <br></br>
        <div className="container">
          <div className="row">
            <div className="card col-md-6 offset-md-3 offset-md-3">
              <h3 className="text-center">Update Course</h3>
              <div className="card-body">
                <form>
                  <div className="form-group">
                    <label> Title: </label>
                    <input
                      placeholder="Title"
                      name="title"
                      className="form-control"
                      value={this.state.title}
                      onChange={this.changeTitleHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Author: </label>
                    <input
                      placeholder="Author"
                      name="authorId"
                      className="form-control"
                      value={this.state.authorId}
                      onChange={this.changeAuthorIdHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Category: </label>
                    <input
                      placeholder="Category"
                      name="category"
                      className="form-control"
                      value={this.state.category}
                      onChange={this.changeCategoryHandler}
                    />
                  </div>

                  <button
                    className="btn btn-success"
                    onClick={this.updateCourse}
                  >
                    Save
                  </button>
                  <button
                    className="btn btn-danger"
                    onClick={this.cancel.bind(this)}
                    style={{ marginLeft: "10px" }}
                  >
                    Cancel
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default UpdateCourseComponent;
