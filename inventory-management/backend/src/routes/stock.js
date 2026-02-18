const express = require('express');
const router = express.Router();

// Placeholder routes
router.get('/', (req, res) => {
    res.json({
        success: true,
        message: 'Stock movements endpoint - coming soon',
        data: []
    });
});

router.post('/in', (req, res) => {
    res.status(501).json({
        success: false,
        message: 'Stock in not yet implemented'
    });
});

router.post('/out', (req, res) => {
    res.status(501).json({
        success: false,
        message: 'Stock out not yet implemented'
    });
});

module.exports = router;
