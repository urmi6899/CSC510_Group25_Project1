
import { combineReducers } from "redux";
import auth from './auth';
import search from './search';


import job from './job';
import application from './application'

export default combineReducers({
    auth,
    search,
    
    
    job,
    application
})

