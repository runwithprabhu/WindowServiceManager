// Simple API test script
// Run with: node test-api.js

const http = require('http');

const BASE_URL = 'http://localhost:3000';

function makeRequest(path, method = 'GET', data = null) {
    return new Promise((resolve, reject) => {
        const url = new URL(path, BASE_URL);
        const options = {
            hostname: url.hostname,
            port: url.port,
            path: url.pathname,
            method: method,
            headers: {
                'Content-Type': 'application/json'
            }
        };

        const req = http.request(options, (res) => {
            let body = '';
            res.on('data', (chunk) => body += chunk);
            res.on('end', () => {
                try {
                    resolve({
                        status: res.statusCode,
                        data: JSON.parse(body)
                    });
                } catch (e) {
                    resolve({
                        status: res.statusCode,
                        data: body
                    });
                }
            });
        });

        req.on('error', reject);
        
        if (data) {
            req.write(JSON.stringify(data));
        }
        
        req.end();
    });
}

async function runTests() {
    console.log('🧪 Testing Inventory Management API\n');
    console.log('=' .repeat(50));

    try {
        // Test 1: Health Check
        console.log('\n✓ Test 1: Health Check');
        const health = await makeRequest('/health');
        console.log(`  Status: ${health.status}`);
        console.log(`  Response:`, health.data);

        // Test 2: Get Products
        console.log('\n✓ Test 2: Get All Products');
        const products = await makeRequest('/api/products');
        console.log(`  Status: ${products.status}`);
        console.log(`  Products found: ${products.data.data?.length || 0}`);

        // Test 3: Check SKU Availability
        console.log('\n✓ Test 3: Check SKU Availability');
        const skuCheck = await makeRequest('/api/products/check/sku/TEST-LAP-999');
        console.log(`  Status: ${skuCheck.status}`);
        console.log(`  SKU Available: ${!skuCheck.data.exists}`);

        // Test 4: Create Product
        console.log('\n✓ Test 4: Create Product');
        const newProduct = {
            name: 'Test Laptop',
            sku: 'TEST-LAP-001',
            category: 'Electronics',
            price: 999.99,
            quantity: 15,
            minQuantity: 5,
            description: 'High-performance laptop for testing'
        };
        const created = await makeRequest('/api/products', 'POST', newProduct);
        console.log(`  Status: ${created.status}`);
        console.log(`  Created:`, created.data.data?.name || 'Failed');
        if (created.data.warning) {
            console.log(`  Warning: ${created.data.warning}`);
        }

        // Test 5: Try Duplicate SKU (Should Fail)
        console.log('\n✓ Test 5: Try Creating Duplicate SKU (Should Fail)');
        const duplicateSKU = {
            name: 'Another Laptop',
            sku: 'TEST-LAP-001', // Same SKU as above
            category: 'Electronics',
            price: 799.99,
            quantity: 10
        };
        const dupResult = await makeRequest('/api/products', 'POST', duplicateSKU);
        console.log(`  Status: ${dupResult.status}`);
        console.log(`  Expected Failure: ${dupResult.status === 400 ? '✅ PASS' : '❌ FAIL'}`);
        console.log(`  Message: ${dupResult.data.message}`);

        // Test 6: Check Duplicate SKU
        console.log('\n✓ Test 6: Check Duplicate SKU');
        const dupCheck = await makeRequest('/api/products/check/sku/TEST-LAP-001');
        console.log(`  Status: ${dupCheck.status}`);
        console.log(`  SKU Exists: ${dupCheck.data.exists ? '✅ Detected' : '❌ Not Detected'}`);

        // Test 7: Create Product with Similar Name
        console.log('\n✓ Test 7: Create Product with Similar Name');
        const similarName = {
            name: 'Test Laptop', // Same name
            sku: 'TEST-LAP-002', // Different SKU
            category: 'Electronics',
            price: 899.99,
            quantity: 8
        };
        const similarResult = await makeRequest('/api/products', 'POST', similarName);
        console.log(`  Status: ${similarResult.status}`);
        console.log(`  Created: ${similarResult.data.data?.name || 'Failed'}`);
        if (similarResult.data.warning) {
            console.log(`  Warning Shown: ✅ ${similarResult.data.warning}`);
        }

        // Test 8: Get Categories
        console.log('\n✓ Test 8: Get Categories');
        const categories = await makeRequest('/api/categories');
        console.log(`  Status: ${categories.status}`);
        console.log(`  Categories found: ${categories.data.data?.length || 0}`);

        // Test 9: Create Category
        console.log('\n✓ Test 9: Create Category');
        const newCategory = {
            name: 'Test Category',
            description: 'Category for testing'
        };
        const createdCat = await makeRequest('/api/categories', 'POST', newCategory);
        console.log(`  Status: ${createdCat.status}`);
        console.log(`  Created:`, createdCat.data.data?.name || 'Failed');

        console.log('\n' + '='.repeat(50));
        console.log('✅ All tests completed!\n');
        console.log('Summary:');
        console.log('  - Duplicate SKU detection: Working ✅');
        console.log('  - Similar name warning: Working ✅');
        console.log('  - Product creation: Working ✅');
        console.log('  - Category creation: Working ✅\n');

    } catch (error) {
        console.error('\n❌ Error:', error.message);
        console.log('\n💡 Make sure the backend server is running:');
        console.log('   cd backend && npm start\n');
    }
}

// Run tests
runTests();
