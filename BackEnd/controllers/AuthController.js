const User =require('../models/User')
const bcrypt = require('bcryptjs')
const jwt = require('jsonwebtoken')

const Signup =(req,res,next) =>{
    bcrypt.hash(req.body.password,10, function (err,hashePass) {
        
        if(err) {
            res.json({
                error:err
            })
        }
        let user = new User({
            name:req.body.name,
            email:req.body.email,
            phone:req.body.phone,
            password:hashePass
         })
      
          user.save()
          .then(user =>{
           
              res.json({
                message:'User added successfully'
              })
        })
          .catch(error =>{

              res.json({
                
                  message:'An error occured!'
              })
              
             
       })
    })    

   
    

}
const login = (req,res,next )=>{
var username =req.body.username
var password =req.body.password

  User.findOne({email:username})
    .then(user =>{
        if(user){
            bcrypt.compare(password,user.password,function(err,result){
                if(err){
                    res.status(404).json({
                        error:err
                    })
                }
                if(result){
                    let token = jwt.sign({name:user.name},'verySecretValue',{expiresIn:'1h'})
                    res.json({
                        message:'Login successfully',
                        token
                    })
                }else{
                    res.status(404).json({
                        message:'Password does not matched'
                    })
                }
            })
        }else{
            res.status(404).json({
                message:'No user found'
            })
        }
    })


}
module.exports = {
    Signup , login
}






