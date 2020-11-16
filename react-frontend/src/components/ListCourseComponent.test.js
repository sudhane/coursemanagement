/* Dependencies */
import React from "react";
import { configure } from "enzyme";
import { shallow, mount } from "enzyme";
import Adapter from "enzyme-adapter-react-16";

/* Components */
import ListCourseComponent from "./ListCourseComponent";

configure({ adapter: new Adapter() });

describe("ListCourseComponent", () => {
  let wrapper;

  beforeEach(() => wrapper = shallow(<ListCourseComponent />));

  it('should render a <div />', () => {
    expect(wrapper.find('div').length).toEqual(3);
  });

  it('renders the values of course', () => {
  
    const beforeUpdateRows = wrapper.find('tr')
    //1 tr is for header
    expect(beforeUpdateRows).toHaveLength(1);

    wrapper.instance().state.courses = [
      { id: 1, title: "Java", authorId: 1, category: "OOPS" },
      { id: 2, title: "Hadoop", authorId: 2, category: "Big Data" },
      { id: 3, title: "Soft Skill", authorId: 3, category: "Soft Skills" },
      { id: 4, title: "Bill of Materials", authorId: 4, category: "Machine Engineering" },
    ]

    wrapper.instance().forceUpdate()

    const courseRows = wrapper.find('tr')
   
    //1 tr is for header, 4 tr for data row
    expect(courseRows).toHaveLength(5);

  });   

  it('delete the course', () => {

    wrapper.instance().state.courses = [
      { id: 1, title: "Java", authorId: 1, category: "OOPS" },
      { id: 2, title: "Hadoop", authorId: 2, category: "Big Data" },
      { id: 3, title: "Soft Skill", authorId: 3, category: "Soft Skills" },
      { id: 4, title: "Bill of Materials", authorId: 4, category: "Machine Engineering" },
    ]
 
    wrapper.instance().forceUpdate()

    const deleteButtonsBeforeClickingDelete = wrapper.find('#deleteCourseButton')
    expect(deleteButtonsBeforeClickingDelete).toHaveLength(4);
    const deleteButton = deleteButtonsBeforeClickingDelete.first()
    deleteButton.props().onClick(1)
    
    wrapper.instance().state.courses = wrapper.instance().state.courses.filter((course) => course.id !== 1)

    wrapper.instance().forceUpdate()

    const deleteButtonsAfterClickingDelete = wrapper.find('#deleteCourseButton')
    expect(deleteButtonsAfterClickingDelete).toHaveLength(3);

  });

});
