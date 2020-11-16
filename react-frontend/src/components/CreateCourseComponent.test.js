/* Dependencies */
import React from "react";
import { configure } from "enzyme";
import { shallow, mount } from "enzyme";
import Adapter from "enzyme-adapter-react-16";

/* Components */
import CreateCourseComponent from "./CreateCourseComponent";

configure({ adapter: new Adapter() });


describe("CreateCourseComponent", () => {

    let wrapper;
  
    beforeEach(() => wrapper = shallow(<CreateCourseComponent />));
  
    it(' submit the form to create a course ', () => {
       
        const inputTitle = wrapper.find("#course_title")
        inputTitle.value =  'Quantum Physics in 21 minutes'

        const inputAuthor = wrapper.find("#course_authorId")
        inputAuthor.value = 1

        const inputCategory = wrapper.find("#course_category")
        inputCategory.value = 'Physics'

        const event = Object.assign(jest.fn(), {preventDefault: () => {}})
    
        const submitButton = wrapper.find("#submitCourseButton")
        submitButton.simulate('click', event)

    });



});   