// Run the Java app first. Then: npm install --no-save playwright
// npx playwright install chromium
// node tools/screenshots.cjs
const {chromium}=require('playwright');
const path=require('node:path');
(async()=>{const browser=await chromium.launch();const page=await browser.newPage({viewport:{width:1365,height:1000}});
const root=path.resolve(__dirname,'../screenshots');
require('node:fs').mkdirSync(root,{recursive:true});
await page.goto('http://localhost:8080/');await page.screenshot({path:path.join(root,'home.png'),fullPage:true});
await page.locator('#name').fill('Demo Player');await page.getByRole('button',{name:'Start game'}).click();await page.screenshot({path:path.join(root,'game.png'),fullPage:true});
// Submit a demonstration attempt; mathematical correctness is tested separately.
for(let i=0;i<4;i++)await page.locator('#a'+i).fill('0');
await page.getByRole('button',{name:'Submit answers'}).click();await page.screenshot({path:path.join(root,'result.png'),fullPage:true});
await page.goto('http://localhost:8080/leaderboard');await page.screenshot({path:path.join(root,'leaderboard.png'),fullPage:true});await browser.close();console.log('Saved four genuine browser screenshots under screenshots/.');
})().catch(e=>{console.error(e);process.exit(1)});
