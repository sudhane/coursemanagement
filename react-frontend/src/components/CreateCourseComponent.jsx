import React, { Component } from "react";
import CourseService from "../services/CourseService";

class CreateCourseComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      // step 2
      id: "_add",
      title: "",
      authorId: "1",
      category: "",
      authors: [],
    };
    this.changeTitleHandler = this.changeTitleHandler.bind(this);
    this.changeAuthorIdHandler = this.changeAuthorIdHandler.bind(this);
    this.saveOrUpdateCourse = this.saveOrUpdateCourse.bind(this);
  }

  // step 3
  componentDidMount() {
    // step 4
    if (this.state.id === "_add") {
      fetch("http://localhost:8080/api/v1/authors")
        .then((response) => {
          return response.json();
        })
        .then((data) => {
          let authorsFromAPI = data.map((author) => {
            return { value: author.id, display: author.fullName };
          });
          this.setState({
            authors: authorsFromAPI,
          });
        })
        .catch((error) => {
          console.log(error);
        });

      return;
    } else {
      CourseService.getCourseById(this.state.id).then((res) => {
        let course = res.data;
        this.setState({
          title: course.title,
          authorId: course.authorId,
          category: course.category,
        });
      });
    }
  }
  saveOrUpdateCourse = (e) => {
    console.log(" Entered saveOrUpdateCourse function ")
    e.preventDefault();
    let course = {
      title: this.state.title,
      authorId: this.state.authorId,
      category: this.state.category,
    };
    console.log("course => " + JSON.stringify(course));

    // step 5
    if (this.state.id === "_add") {
      CourseService.createCourse(course).then((res) => {
        this.props.history.push("/courses");
      });
    } else {
      CourseService.updateCouse(course, this.state.id).then((res) => {
        this.props.history.push("/courses");
      });
    }
  };

  changeTitleHandler = (event) => {
    this.setState({ title: event.target.value });
  };

  changeAuthorIdHandler = (event) => {
    this.setState({ authorId: parseInt(event.target.value) });
  };

  changeCategoryHandler = (event) => {
    this.setState({ category: event.target.value });
  };

  cancel() {
    this.props.history.push("/courses");
  }

  getTitle() {
    if (this.state.id === "_add") {
      return <h3 id="addCourseHeader" className="text-center">Add Course</h3>;
    } else {
      return <h3 className="text-center">Update Course</h3>;
    }
  }
  render() {
    return (
      <div>
        <br></br>
        <div className="container"  >
          <div className="row">
            <div className="card col-md-6 offset-md-3 offset-md-3">
              {this.getTitle()}
              <div className="card-body">
                <form>
                  <div className="form-group">
                    <label> Title: </label>
                    <input
                      placeholder="Title"
                      name="title"
                      id="course_title"
                      className="form-control"
                      value={this.state.title}
                      onChange={this.changeTitleHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Author : </label>
                    <select
                      id="course_authorId"
                      value={this.state.authorId}
                      onChange={(e) =>
                        this.setState({ authorId: e.target.value })
                      }
                    >
                      {this.state.authors.map((author, index) => (
                        <option key={index} value={author.value}>
                          {author.display}
                        </option>
                      ))}
                    </select>
                  </div>
                  <div className="form-group">
                    <label> Category: </label>
                    <input
                      placeholder="Category"
                      name="category"
                      id="course_category"
                      className="form-control"
                      value={this.state.category}
                      onChange={this.changeCategoryHandler}
                    />
                  </div>

                  <button
                    className="btn btn-success"
                    id="submitCourseButton"
                    onClick={this.saveOrUpdateCourse}
                  >
                    Save
                  </button>
                  <button
                    className="btn btn-danger"
                    id="cancelCourseButton"
                    onClick={this.cancel.bind(this)}
              
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

export default CreateCourseComponent;
