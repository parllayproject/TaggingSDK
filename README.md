Tags Demo Using Tagging SDK
===========================

This is a demo app which explains the integration of [Parllay Tags](https://tags.parllay.com) in an Android app.

## How to include Library

To get a Git project into your build:

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }


**Step 2.** Add the dependency

    dependencies {
        implementation 'com.github.parllayproject:TaggingSDK:v0.1.0'
    }

Usage
-----
To use this library, initially visit [Parllay Tags](https://tags.parllay.com) website and complete the required steps to generate a tag url.

# Setup
Initialize the library in you Application class as follows.

    Tagging.start(this)

# Tagging Event Trigger
Use the library to trigger events by using the tag urls obtained from [Parllay Tags](https://tags.parllay.com)

**Without Extra Values**

    Tagging.tagEvent("your.tag.url")

**With Extra Values**

    val bundle = Bundle()
    bundle.putString("param1","value1")
    bundle.putString("param2","value2")

    Tagging.tagEvent("your.tag.url",bundle)

License
-------
    Copyright © 2023 Parllay. a division of Parllay Inc.
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
