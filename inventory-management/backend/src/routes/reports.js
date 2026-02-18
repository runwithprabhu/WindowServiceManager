const express = require('express');
const router = express.Router();

// Placeholder routes
router.get('/inventory', (req, res) => {
    res.json({
        success: true,
        message: 'Inventory report - coming soon',
        data: {}
    });
});

router.get('/sales', (req, res) => {
    res.json({
        success: true,
        message: 'Sales report - coming soon',
        data: {}
    });
});

module.exports = router;
