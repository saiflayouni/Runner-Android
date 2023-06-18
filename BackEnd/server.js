const express = require('express')
const mongoose = require('mongoose')
const morgan = require('morgan')
const cors = require('cors')


const ClientRoute = require('./routes/client')
const AuthRoute = require('./routes/auth')
const articlesRoute = require('./routes/rss')


mongoose.connect('mongodb://localhost:27017/testbd', { useNewUrlParser: true, useUnifiedTopology: true })
const db = mongoose.connection

db.on('error', (err) => {
    console.log(err)
})

db.once('open', () => {
    console.log('  Database connected')
})


const app = express()

app.use(cors())
app.use(morgan('dev'))
app.use(express.json())
app.use(express.urlencoded({ extended: true }))
app.use('/uploads', express.static('uploads'))

const PORT = process.env.PORT || 3000
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`)
})
app.use('', AuthRoute)
app.use('/articles', articlesRoute)
app.use('/api/client', ClientRoute)
