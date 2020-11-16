import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListCourseComponent from './components/ListCourseComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateCourseComponent from './components/CreateCourseComponent';
import ViewCourseComponent from './components/ViewCourseComponent';

function App() {
  return (
    <div>
        <Router>
              <HeaderComponent />
                <div className="container">
                    <Switch> 
                          <Route path = "/" exact component = {ListCourseComponent}></Route>
                          <Route path = "/courses" component = {ListCourseComponent}></Route>
                          <Route path = "/add-course/:id" component = {CreateCourseComponent}></Route>
                          <Route path = "/view-course/:id" component = {ViewCourseComponent}></Route>
                        
                    </Switch>
                </div>
         
        </Router>
    </div>
    
  );
}

export default App;
