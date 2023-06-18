const { runRssParser } = require('../controllers/RssController.js')

const { Router } = require("express")

const router = Router()

router.route('/').get(runRssParser)

module.exports = router 