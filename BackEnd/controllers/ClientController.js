
const Client = require('../models/Client')
const index =(req, res,next) =>{
    Client.find()
    .then(response =>{
        res.json({
            response
        })
    })
    .catch(error =>{
        res.json({
            message:'An error occured'
        })
    })
}
const show =(req, res,next) =>{
    let clientId = req.body.clientId
    Client.findById(clientId)
    .then(response =>{
        res.json({
            response
        })
    })
    .catch(error =>{
        res.json({
            message:'An error occured'
        })
    })
}
const store =(req, res,next) =>{
    let client = new Client({
        name:req.body.name,
        email:req.body.email,
        phone:req.body.phone,
        age:req.body.age,
        password:req.body.password
    })
    //if(req.file){
       // let path = ''
       // req.files.forEach(function(file,index,arr) {
       //     path = path + file.path + ','
            
       // })
       // path =  path.substring(0,path.lastIndexOf(","))
       // client.avatar = path
   // }
    if(req.file){
        client.avatar = req.file.path
    }

    client.save()
    .then(response =>{
        res.json({
            message:'Client added successfully'
        })
    })
    .catch(error =>{
        res.json({
            message:'An error occured'
        })
    })
}
const update =(req, res,next) =>{
    let clientId = req.body.clientId
    let updateData = {
        name:req.body.name,
        email:req.body.email,
        phone:req.body.phone,
        age:req.body.age,
        //password:req.body.password
    }
    Client.findByIdAndUpdate(clientId,{$set:updateData})
    .then(() =>{
        res.json({
            message:'Client updated successfully'
        })
    })
    .catch(error =>{
        res.json({
            message:'An error occured'
        })
    })
}
//delete client
const destroy =(req, res,next) =>{
    let clientId = req.body.clientId
    Client.findByIdAndRemove(clientId)
    .then(() =>{
        res.json({
            message:'Client deleted successfully'
        })
    })
    .catch(error =>{
        res.json({
            message:'An error occured'
        })
    })
}

module.exports = {
    index, show, store, update, destroy
}