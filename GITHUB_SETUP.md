# GitHub Setup Guide

This guide will help you commit this project to GitHub.

## Prerequisites

### 1. Install Git
Download and install Git from: https://git-scm.com/download/win

During installation:
- Select "Git from the command line and also from 3rd-party software"
- Use default settings for other options

### 2. Create GitHub Account
If you don't have one: https://github.com/signup

### 3. Configure Git (First Time Only)
Open Command Prompt or PowerShell and run:
```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

## Step-by-Step GitHub Upload

### Step 1: Initialize Git Repository
Open Command Prompt in the project folder and run:
```bash
git init
```

### Step 2: Add All Files
```bash
git add .
```

### Step 3: Create Initial Commit
```bash
git commit -m "Initial commit: Windows Services & Process Manager v1.0"
```

### Step 4: Create GitHub Repository
1. Go to https://github.com/new
2. Repository name: `windows-services-manager`
3. Description: `Portable web-based Windows services and process manager`
4. Choose Public or Private
5. **DO NOT** initialize with README (we already have one)
6. Click "Create repository"

### Step 5: Link Local Repository to GitHub
Replace `YOUR_USERNAME` with your GitHub username:
```bash
git remote add origin https://github.com/YOUR_USERNAME/windows-services-manager.git
```

### Step 6: Push to GitHub
```bash
git branch -M main
git push -u origin main
```

### Step 7: Enter Credentials
- Username: Your GitHub username
- Password: Use Personal Access Token (not your password)

#### How to Create Personal Access Token:
1. Go to GitHub Settings → Developer settings → Personal access tokens → Tokens (classic)
2. Click "Generate new token (classic)"
3. Give it a name: "Windows Services Manager"
4. Select scopes: `repo` (full control of private repositories)
5. Click "Generate token"
6. Copy the token (you won't see it again!)
7. Use this token as password when pushing

## Quick Command Reference

```bash
# Check status
git status

# Add new files
git add .

# Commit changes
git commit -m "Your commit message"

# Push to GitHub
git push

# Pull latest changes
git pull

# View commit history
git log

# Create new branch
git checkout -b feature-name

# Switch branches
git checkout main
```

## Alternative: GitHub Desktop

If you prefer a GUI:
1. Download GitHub Desktop: https://desktop.github.com/
2. Install and sign in
3. Click "Add" → "Add existing repository"
4. Select your project folder
5. Click "Publish repository"
6. Choose name and visibility
7. Click "Publish repository"

## Updating Your Repository

After making changes:
```bash
# Check what changed
git status

# Add changes
git add .

# Commit with message
git commit -m "Description of changes"

# Push to GitHub
git push
```

## Common Issues

### Issue: Git not recognized
**Solution**: Install Git and restart Command Prompt

### Issue: Permission denied
**Solution**: Use Personal Access Token instead of password

### Issue: Repository already exists
**Solution**: 
```bash
git remote remove origin
git remote add origin https://github.com/YOUR_USERNAME/new-repo-name.git
```

### Issue: Merge conflicts
**Solution**:
```bash
git pull --rebase
# Fix conflicts in files
git add .
git rebase --continue
git push
```

## Repository Settings

### Add Topics (Tags)
On GitHub repository page:
- Click "Add topics"
- Add: `windows`, `java`, `angularjs`, `process-manager`, `service-manager`, `rest-api`

### Add Description
Edit repository description:
"Portable web-based application for monitoring and managing Windows services and processes"

### Enable Issues
Settings → Features → Check "Issues"

### Add README Badges
Already included in README.md:
- License badge
- Java version badge
- Platform badge

## Sharing Your Project

Your repository URL will be:
```
https://github.com/YOUR_USERNAME/windows-services-manager
```

Share this link with others!

## Clone on Another Machine

To download your project on another computer:
```bash
git clone https://github.com/YOUR_USERNAME/windows-services-manager.git
cd windows-services-manager
LAUNCH.bat
```

## Next Steps

1. Add screenshots to README
2. Create releases for versions
3. Add contributing guidelines
4. Set up GitHub Actions for CI/CD
5. Add issue templates

---

Need help? Check GitHub documentation: https://docs.github.com/
