const express = require('express');
const router = express.Router();

// Placeholder routes - implement when Supplier model is created
router.get('/', (req, res) => {
    res.json({
        success: true,
        message: 'Suppliers endpoint - coming soon',
        data: []
    });
});

router.post('/', (req, res) => {
    res.status(501).json({
        success: false,
        message: 'Suppliers creation not yet implemented'
    });
});

module.exports = router;
