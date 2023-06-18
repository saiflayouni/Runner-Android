const mongoose   =require('mongoose')
const Schema     =mongoose.Schema
const clientSchema = new Schema({
    name:{

        type:String
    },
    email:{
        type:String
    },
    phone:{
        type:String
    },
    age:{   
        type:Number
    }  , 
    password:{   
        type:String
    }   ,
    avatar:{   
        type:String
    }   

},{timestamps: true})
const Client = mongoose.model('Client',clientSchema)
module.exports = Client
 