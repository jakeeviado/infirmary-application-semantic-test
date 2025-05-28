#!/bin/bash

set -e

NEXT_VERSION="$1"
BASE_BRANCH="master"

echo "Creating release branch: release-$NEXT_VERSION"
git checkout -b "release-$NEXT_VERSION"

echo "Adding pom.xml and CHANGELOG.md"
git add pom.xml CHANGELOG.md

echo "Committing changes for release $NEXT_VERSION"
git commit -m "chore(release): $NEXT_VERSION [skip ci]"

echo "Pushing release branch to origin"
git push origin "release-$NEXT_VERSION"

echo "Creating GitHub Pull Request"
if command -v gh &> /dev/null; then
  gh pr create --title "RELEASE $NEXT_VERSION" --body "New release for version $NEXT_VERSION" --base "$BASE_BRANCH"
else
  echo "Warning: GitHub CLI ('gh') not found. Please install it to automatically create a Pull Request."
  echo "You'll need to create the Pull Request manually for branch 'release-$NEXT_VERSION'."
fi

echo "Release branch 'release-$NEXT_VERSION' created and pushed. Pull Request creation attempted."